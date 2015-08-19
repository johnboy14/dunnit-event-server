(ns dunnit.server.component
  (:require [com.stuartsierra.component :as comp]
            [org.httpkit.server :refer [run-server]]))

(defrecord WebServer [config server handler]
  comp/Lifecycle
  (start [component]
    (println "Starting web server on port " (:port config))
    (let [server (run-server handler config)]
      (assoc component :server server)))
  (stop [component]
    (println "Shutting down web server on port " (:port config))
    (when server
      (server)
      component)))

(defn new-web-server [port handler]
  (map->WebServer {:config {:port port} :handler handler}))
