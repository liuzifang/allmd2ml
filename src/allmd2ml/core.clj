(ns allmd2ml.core
  (:gen-class)
  (:require [zifile.core :as zf]
            [markdown.core :as md])
  (:import [java.io File FileReader BufferedReader FileWriter]
           [java.io BufferedWriter]))

(def mdlist
  (for [x (zf/foldlist "." :file)
        :let [y (.getPath x)]
        :when (or (.endsWith y ".md") (.endsWith y ".markdown"))]
    x))

(def mlist 
  (map #(.replace (.replace % ".md" ".html") ".markdown" ".html") 
    (let [premlist (atom [])]
      (#(when-not (empty? %) 
          (swap! premlist conj (.getPath (first %))) 
          (recur (rest %)))
        mdlist)
      @premlist)))

(defn read-str [file]
  (let [readbuf (BufferedReader. (FileReader. file)) content (atom "")]
    (#(when-let [line (.readLine %)]
        (swap! content str (str line "\r\n"))
        (recur %)) 
      readbuf)
    @content))

(defn gen-ml [mdlist mlist]
  (let [md-content (read-str (first mdlist))]
    (zf/writefile (first mlist) (md/md-to-html-string md-content)))
  (when-not (empty? (rest mlist)) (recur (rest mdlist) (rest mlist))))

(defn -main [& args]
  (gen-ml mdlist mlist))
