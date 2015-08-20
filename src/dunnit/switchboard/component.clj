(ns dunnit.switchboard.component
  (:require [com.stuartsierra.component :as comp]
            [clojure.core.async :as c]
            [clojure.data.codec.base64 :as b64]
            [cheshire.core :as ch]))

(defn decode-msg [msg]
  (ch/parse-string
    (->> (b64/decode (.getBytes (:data (:message msg))))
      (map char)
      (reduce str)) true))

(defrecord SwitchBoard [notification-chan push-chan]
  comp/Lifecycle
  (start [component]
    (println "Starting SwitchBoard")
    (c/pipeline 1 (:push-chan push-chan) (map decode-msg) (:notification-chan notification-chan))
    component)
  (stop [component]
    (println "Stopping SwitchBoard")
    component))

(defn switchboard []
  (map->SwitchBoard {}))
