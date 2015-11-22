(ns simlab.util
  (:require
   [criterium.core :as cc]))

(def *execution-samples* 10)

(defn dotime [f & args]
  (let [ctr *execution-samples*
        [x & xs :as res]
        (for [i (range ctr)]
          (cc/time-body (apply f args)))
        execs (map #(quot % 1000000) (map first res))
        mini (apply min execs)
        maxi (apply max execs)
        avg (/ (reduce + execs) ctr 1.0)]
    (println "Result : " (second x))
    (println (str "Executed " ctr " times..."))
    (println "Average execution time : " avg "ms")
    (println "The longest execution time : " maxi "ms")
    (println "The fastest execution time : " mini "ms")))
