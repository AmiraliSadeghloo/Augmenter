(ns augmenter.core
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
;;////////////////////////////////
(defn inp []
  (println "Input map")
  (let [input (read)]
    input))

(def input (inp))

;;////////////////////////////////                          ;;turns maps into vector
(def vectorized (into [] (map vec (partition 2 (reduce into [] input)))))
;;////////////////////////////////
;;  (get (last (nth vectorized 3)) :input)
;;  (vector (first (nth vectorized 2)))
;;  (some #(= (first (nth vectorized 2)) %) (get (last (nth vectorized 3)) :input))
;;///////////////////////////////                         ;;bubble steps
(defn bubble [one two]
  (let [lastOne (last one)]
    (if (and (some #(= % (first two)) (get (last lastOne) :input))
             (some #(= % (first lastOne)) (get (last two) :input)))
      (throw (Exception. "impossible to sort input"))
      (if (some #(= % (first two)) (get (last lastOne) :input))
        (conj (pop one) two lastOne)
        (conj one two)))))
;;///////////////////////////////
(defn bubble-sort [vectorized]
  (let [output (reduce bubble [(first vectorized)] (rest vectorized))]
    (if (= output vectorized)
      vectorized
      (recur output))))
(bubble-sort vectorized)
;;///////////Done////////////////
