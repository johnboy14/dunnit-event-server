(ns dunnit.channel.component
  (:require [com.stuartsierra.component :as comp]
            [clojure.core.async :refer [chan]])
  (:import (clojure.lang Keyword)))

(defrecord Channel [^Keyword name ^Integer buffer-size]
  comp/Lifecycle
  (start [component]
    (println "Creating Channel")
    (assoc component name (chan buffer-size)))
  (stop [component]
    (println "Destroying Channel")
    (dissoc component name nil)))

(defn new-channel [name buffer-size]
  (map->Channel {:name name :buffer-size buffer-size}))