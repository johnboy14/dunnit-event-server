(ns dunnit.switchboard.component
  (:require [com.stuartsierra.component :as comp]
            [clojure.core.async :as c]))

(defrecord SwitchBoard [notification-chan push-chan]
  comp/Lifecycle
  (start [component]
    (println "Starting SwitchBoard")
    (c/pipe (:notification-chan notification-chan) (:push-chan push-chan))
    component)
  (stop [component]
    (println "Stopping SwitchBoard")
    component))

(defn switchboard []
  (map->SwitchBoard {}))
