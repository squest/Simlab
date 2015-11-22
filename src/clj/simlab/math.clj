(ns simlab.math
  (:require
   [incanter.core :as i]
   [incanter.charts :as ic]
   [incanter.stats :as is]
   [incanter.io :as io]))


(defn plot-1 []
  (ic/function-plot
   (fn [x] (+ (* -2 x x x) (* 12 x x) (* -3 x) (- 12)))
   -2 5))




