(ns dunnit.routes.component
  (:require [com.stuartsierra.component :as comp]
            [compojure.core :refer [defroutes routes]]
            [compojure.handler :as handler]
            [compojure.route :as route]
            [compojure.core :refer :all]
            [ring.middleware.resource :refer [wrap-resource]]
            [ring.middleware.json :refer [wrap-json-body wrap-json-params wrap-json-response]]
            [dunnit.resource.notification :refer [notification]]))

(defn construct-routes [channel]
  (defroutes app-routes
    (GET "/" [] "HELLO JOHN")
    (POST "/notification" req (notification req channel))
    (route/resources "/")
    (route/not-found "Not Found")))

(defn app [channel]
  (-> (routes (construct-routes channel))
      (wrap-json-body {:keywords? true})
      (wrap-json-response)
      (handler/site)))

(defrecord NotificationRouteHandler [notification-chan]
  comp/Lifecycle
  (start [component]
    (println "Starting Notification Router")
    (assoc component :notification-route-handler (app (:notification-chan notification-chan))))
  (stop [component]
    (println "Stopping Notification Router")
    (assoc component :notification-route-handler nil)))

(defn new-notification-handler []
  (map->NotificationRouteHandler {}))