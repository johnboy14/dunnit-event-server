(ns dunnit.notification.component
  (:require [com.stuartsierra.component :as comp]
            [dunnit.notification.handler :as h]))

(defrecord NotificationHandler [notification-chan]
  comp/Lifecycle
  (start [component]
    (println "Starting Notification Handler")
    (h/process-notifications-from-chan (:notification-chan notification-chan))
    (assoc component :notification-chan (:notification-chan notification-chan)))
  (stop [component]
    (println "Shutting down Notification Handler")
    (dissoc component :notification-chan nil)))

(defn new-notification-handler []
  (map->NotificationHandler {}))
