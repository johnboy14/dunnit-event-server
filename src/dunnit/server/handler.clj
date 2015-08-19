(ns dunnit.server.handler
  (:require [compojure.core :refer [defroutes routes]]
            [compojure.handler :as handler]
            [compojure.route :as route]
            [compojure.core :refer :all]
            [ring.middleware.resource :refer [wrap-resource]]
            [ring.middleware.json :refer [wrap-json-body wrap-json-params wrap-json-response]]
            [dunnit.resource.notification :refer [notification]]))

(defroutes app-routes
 (GET "/" [] "HELLO JOHN")
 (POST "/notification" _ notification)
 (route/resources "/")
 (route/not-found "Not Found"))

(def app
  (-> (routes app-routes)
      (wrap-json-body {:keywords? true})
      (wrap-json-response)
      (handler/site)))

