(ns dunnit.server.server-test
  (:use midje.sweet)
  (:require [ring.mock.request :refer [request]]
            [dunnit.server.handler :refer [app]]))

(facts ""
  (fact "When given a push notification, parse payload and dispatch on Notification channel"
        ))
