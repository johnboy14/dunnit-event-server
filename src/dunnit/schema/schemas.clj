(ns dunnit.schema.schemas
  (:require [schema.core :refer [validate Str check]]))

(def Notification
  {:message {:data Str
             :message_id Str}
   :subscription Str})

(defn validate-notification [notification]
  (check Notification notification))