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

;删除过程中多获得了一个无意义的对象，这是三个冗余设计中的其中一个。不过我认为要是节约这点资源的话，就压根不应该使用这个程序。它本来就是在积压太多md时使用的。
(defn gen-ml [mdlist mlist]
  (let [md-content (read-str (first mdlist))]
    (let [file (File. (first mlist))] (when (.exists file) (.delete file)))
    (zf/writefile (first mlist) (md/md-to-html-string md-content)))
  (when-not (empty? (rest mlist)) (recur (rest mdlist) (rest mlist))))

(defn -main [& args]
  (gen-ml mdlist mlist))
