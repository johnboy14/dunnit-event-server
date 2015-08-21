(ns dunnit.redis.component
  (:require [com.stuartsierra.component :as comp]
            [taoensso.carmine :as car :refer (wcar)]
            [clojure.core.async :as c]))

(defn start-pushing [config channel]
  (c/go-loop []
    (let [val (c/<!! channel)]
      (println val)
      (if-not (nil? val)
        (do (car/wcar config (car/publish "dunnit" val))
            (recur))))))

(defrecord Redis [config push-chan]
  comp/Lifecycle
  (start [component]
    (println "Starting Redis")
    (let [config {:pool {} :spec {:uri "redis://h:pdfb030ba8v6a76snvhso5m0eda@ec2-54-217-234-142.eu-west-1.compute.amazonaws.com:9849"}}]
      (start-pushing config (:push-chan push-chan)))
    component)
  (stop [component]
    (println "Stopping Redis")
    component))

(defn new-redis-client [config]
  (map->Redis {:config config}))