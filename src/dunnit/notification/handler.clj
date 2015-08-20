(ns dunnit.notification.handler
  (:require [clojure.core.async :as c]))

(defn process-notifications-from-chan [notification-chan]
  (c/go-loop []
    (let [val (c/<!! notification-chan)]
      (if-not (nil? val)
        (do (println "Notification Received" val)
            (c/>!! notification-chan val)
            (recur))))))