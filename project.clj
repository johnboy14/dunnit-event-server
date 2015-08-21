(defproject dunnit "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [com.stuartsierra/component "0.2.1"]
                 [compojure "1.1.6"]
                 [ring/ring-json "0.3.1"]
                 [ring-server "0.3.1"]
                 [ring/ring-json "0.3.1"]
                 [prismatic/schema "0.4.3"]
                 [http-kit "2.1.18"]
                 [liberator "0.13"]
                 [cheshire "5.5.0"]
                 [com.taoensso/carmine "2.11.1"]
                 [org.clojure/data.codec "0.1.0"]
                 [org.clojure/core.async "0.1.346.0-17112a-alpha"]]
  :profiles {:dev {:dependencies [[org.clojure/tools.namespace "0.2.4"]
                                  [midje "1.7.0"]
                                  [ring-mock "0.1.5"]
                                  [ring/ring-devel "1.3.1"]
                                  [midje "1.7.0"]]
                   :plugins [[lein-midje "3.1.3"]]
                   :source-paths ["dev"]}}
  :main dunnit.system)
