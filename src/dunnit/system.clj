(ns dunnit.system
  (:require [com.stuartsierra.component :as component]
            [dunnit.server.component :as server]
            [dunnit.notification.component :as notification]
            [dunnit.channel.component :as channels]
            [dunnit.routes.component :as router]
            [dunnit.switchboard.component :as switchboard]
            [dunnit.redis.component :as redis]
            [clojure.core.async :refer [chan]]))


(defn new-system []
  (component/system-map
   :notification-chan (channels/new-channel :notification-chan 10)
   :push-chan (channels/new-channel :push-chan 10)
   :switchboard (component/using (switchboard/switchboard)
                                 {:notification-chan :notification-chan
                                  :push-chan :push-chan})
   :notification-router (component/using (router/new-notification-handler)
                                         {:notification-chan :notification-chan})
   :notification-handler (component/using (notification/new-notification-handler)
                                          {:notification-chan :notification-chan})
   :redis-client (component/using (redis/new-redis-client {})
                                  {:push-chan :push-chan})
   :web-server (component/using (server/new-web-server 8080)
                                {:handler :notification-router})))

(def system nil)

(defn init
  "Constructs the current development system."
  []
  (alter-var-root #'system
                  (constantly (new-system))))

(defn -main [& args]
  (init)
  (alter-var-root #'system component/start))
