(defproject simlab "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]
                 ;; [incanter "1.5.6"]
                 [quil "2.2.6"]
                 [org.clojure/clojurescript "0.0-3308"]
                 [criterium "0.4.3"]]

  :plugins [[lein-cljsbuild "1.0.6"]]
  :hooks [leiningen.cljsbuild]

  :cljsbuild
  {:builds [{:source-paths ["src/cljs"]
             :compiler
             {:output-to "js/main.js"
              :output-dir "out"
              :main "simlab.alfa"
              :optimizations :none
              :pretty-print true}}]})
