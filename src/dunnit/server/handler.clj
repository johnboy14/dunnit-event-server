(ns dunnit.server.handler
  (:require [compojure.core :refer [defroutes routes]]
            [compojure.handler :as handler]
            [compojure.route :as route]
            [compojure.core :refer :all]))

(defroutes app-routes
 (GET "/" [] "HELLO JOHN")
 (route/resources "/")
 (route/not-found "Not Found"))

(def app
  (-> (routes app-routes)
      (handler/site)))

