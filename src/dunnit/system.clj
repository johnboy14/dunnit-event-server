(ns dunnit.system
  (:require [com.stuartsierra.component :as component]
            [dunnit.server.component :as server]
            [dunnit.server.handler :refer [app]]))

(defn new-system []
  (component/system-map 
   :web-server (server/new-web-server 8080 app)))
