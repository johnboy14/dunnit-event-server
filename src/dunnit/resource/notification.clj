(ns dunnit.resource.notification
  (:require [liberator.core :refer [defresource]]
            [dunnit.schema.schemas :refer [validate-notification]]
            [clojure.core.async :as c]))

(defn retrieve-body [payload]
  (or (get-in payload [:body]) {}))

(defresource notification [payload channel]
 :available-media-types ["application/json"]
 :malformed? (fn [ctx] (validate-notification (retrieve-body payload)))
 :allowed-methods [:post]
 :post! (fn [ctx] (c/>!! channel (retrieve-body payload)))
 :handle-created "")