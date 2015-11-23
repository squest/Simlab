(ns simlab.alfa
  (:require
   [quil.core :as q :include-macros true]
   [quil.middleware :as m]))
        
(defn setup []
  ;; Set frame rate to 30 frames per second.
  (q/frame-rate 30)
  ;; Set color mode to HSB (HSV) instead of default RGB.
  (q/color-mode :hsb)
  ;; setup function returns initial state. It contains
  ;; circle color and position.
  {:x -200})

(defn update-state [state]
  ;; Update sketch state by changing circle color and position.
  {:x (+ 1 (:x state))})

(defn draw-state [state]
  ;; Set circle color.
  (q/fill 150 255 255)
  ;; Calculate x and y coordinates of the circle.
  (let [{:keys [x]} state
        scale -0.005
        y (* scale (+ (* 0.005 x x x) (* -2 x x) (* 30 x) -100))]
    (q/with-translation [(/ (q/width) 2)
                         (/ (q/height) 2)]
      ;; Draw the circle.
      (q/ellipse x y 2 2)
      (q/ellipse y (* 5 x) 2 2)
      (q/line -500 0 500 0)
      (q/line 0 -300 0 300))))

(q/defsketch simlab
  :host "simlab"
  ;; the size of the canvas
  :size [1100 600]
  ;; setup function called only once, during sketch initialization.
  :setup setup
  ;; update-state is called on each iteration before draw-state.
  :update update-state
  :draw draw-state
  :middleware [m/fun-mode])
