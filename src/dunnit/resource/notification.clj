(ns dunnit.resource.notification
  (:require [liberator.core :refer [defresource]]
            [dunnit.notification.handler :refer [process-notification]]
            [dunnit.schema.schemas :refer [validate-notification]]))

(defn retrieve-body [payload]
  (or (get-in payload [:body]) {}))

(defresource notification [payload]
 :available-media-types ["application/json"]
 :malformed? (fn [ctx] (validate-notification (retrieve-body payload)))
 :allowed-methods [:post]
 :post! (fn [ctx] (process-notification (retrieve-body payload)))
 :handle-created :record)