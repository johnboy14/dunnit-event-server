(ns dunnit.server.server-test
  (:use midje.sweet)
  (:require [ring.mock.request :refer [request content-type]]
            [dunnit.server.handler :refer [app]]
            [cheshire.core :refer [generate-string]]))

(def valid-payload (generate-string {:message {:data "eyJlbWFpbEFkZHJlc3MiOiAidXNlckBleGFtcGxlLmNvbSIsICJoaXN0b3J5SWQiOiAiMTIzNDU2Nzg5MCJ9"
                                               :message_id "1234567890"}
                                     :subscription "dunnit"}))

(facts ""
  (fact "When given a push notification, parse payload and dispatch on Notification channel"
        (let [response (app (content-type (request :post "/notification" valid-payload) "application/json"))]
          (:status response) => 201
          (:body response) => (generate-string {:message_id "1234567890"}))))
