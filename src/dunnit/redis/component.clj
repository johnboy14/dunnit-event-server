(ns dunnit.redis.component
  (:require [com.stuartsierra.component :as comp]
            [taoensso.carmine :as car :refer (wcar)]
            [clojure.core.async :as c]
            [cheshire.core :as ch]))

(defn retrieve-event-details [notification]
  (let [{:keys [emailAddress historyId]} (ch/parse-string notification true)]
    (-> {:emailAddress emailAddress :historyId historyId :message "DUNNIT GOES HERE!!!"}
        (ch/generate-string))))

(defn start-pushing [config channel]
  (c/go-loop []
    (let [val (retrieve-event-details (c/<!! channel))]
      (println val)
      (if-not (nil? val)
        (do (car/wcar config (car/publish "dunnit" val))
            (recur))))))

(defrecord Redis [config push-chan]
  comp/Lifecycle
  (start [component]
    (println "Starting Redis")
    (let [config {:pool {} :spec {}}]
      (start-pushing config (:push-chan push-chan)))
    component)
  (stop [component]
    (println "Stopping Redis")
    component))

(defn new-redis-client [config]
  (map->Redis {:config config}))