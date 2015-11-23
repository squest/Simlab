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

(defn setup-1 []
  (q/frame-rate 1)
  (q/color-mode :hsb)
  {:x 200 :y 200 :len 50})

(defn vek [[a b] [x y]]
  (let [[c d] [(+ x a) (+ y b)]]
    (q/line x y c d)
    (q/ellipse x y 3 3)))

(defn update-state-1 [state]
  (let [{:keys [x y len]} state]
    {:x (inc x) :y (* 2 y)
     :len (rand-int 50)
     :angle (rand-int 180)}))

(defn draw-state-1 [state]
  (q/fill 150 200 100)
  (let [{:keys [x y len]} state]
    (vek [x y] [(rand-int 50) (rand-int 50)])))

(defn get-setup []
  (q/frame-rate 30)
  (q/color-mode :hsb)
  (q/fill 130 230 230))

(defn setup-2 []
  (get-setup)
  {:x -10 :xt 10 :step 0.04
   :fun (fn [x] (+ (* x x x) (* -5 x x) (* -10 x) 10))})

(defn update-state-2 [state]
  (let [{:keys [x xt step fun]} state]
    (if (> x xt)
      (no-loop)
      (assoc state :x (+ step x)))))

(defn draw-state-2 [state]
  (q/fill 130 230 230)
  (let [scale 50
        {:keys [x fun]} state
        ys (* -1 0.05 scale (fun x))
        xs (* scale x)] 
    (q/with-translation [(/ (q/width) 2)
                         (/ (q/height) 2)]
      (q/ellipse xs ys 2 2)
      (q/line -500 0 500 0)
      (q/line 0 -300 0 300)
      (vek [xs ys] [(+ xs 30) (+ ys 30)]))))

(q/defsketch simlab
  :host "simlab"
  ;; the size of the canvas
  :size [1100 600]
  ;; setup function called only once, during sketch initialization.
  :setup setup-2
  ;; update-state is called on each iteration before draw-state.
  :update update-state-2
  :draw draw-state-2
  :middleware [m/fun-mode])
