(defproject allmd2ml "1.0.0"
  :description "turn all md to html in current fold."
  :url "https://github.com/liuzifang/allmd2ml"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [markdown-clj "0.9.41"]
                 ;zifile is my unstable clojure lib
                 [zifile "0.2.0"]]
  :main allmd2ml.core
  :aot [allmd2ml.core])
