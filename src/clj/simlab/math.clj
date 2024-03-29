(ns simlab.math
  (:require
   [incanter.core :refer [view] :as i]
   [incanter.charts :refer [function-plot] :as ic]
   [simlab.util :refer [dotime]]))

(defn plot-1 [a b]
  (ic/function-plot
   (fn [x] (+ (* -0.5 x x x) (* 12 x x) (* -3 x) (- 12)))
   a b))


(defn plot-2 [a b]
  (ic/function-plot
   (fn [x] (+ (* 3 (i/sin x)) x (- (* 5 (i/cos (* 1/8 x))))))
   a b))

(defn expt [^long a ^long m]
  (cond
   (== m 0) 1N
   (== m 1) a
   :else (let [res (expt a (quot m 2))]
           (if (even? m)
             (*' res res)
             (*' res res a)))))




