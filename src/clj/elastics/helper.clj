(ns elastics.helper
  (:require [clojure.java.io :as io]
            [clj-http.client :as http]
            [cheshire.core :as json])
  (:import [java.net URL]))


(defn file
  "Creates a file path"
  [& more]
  (let [paths (remove nil? more)]
    (str (when (seq paths)
           (apply io/file (map str paths))))))


(defn create-url
  "Creates url by combining two strings"
  [url file]
  (str (URL. (URL. url) file)))


