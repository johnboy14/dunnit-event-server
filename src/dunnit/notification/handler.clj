(ns dunnit.notification.handler)

(defn process-notification [payload]
  {:record {:message_id (:message_id (get-in payload [:message]))}})