(ns elastics.search
  (:refer-clojure :exclude [count])
  (:require [elastics.helper :as helper]))


;;; -----------------------------------------------
;;; -----------  helper functions -----------------
;;; -----------------------------------------------

(def extend-url helper/extend-url)

(def merge-http-map helper/merge-http-map)

(def params helper/params)

(def custom helper/custom)

(def run helper/run)



;;; -----------------------------------------------
;;; -----------  search functions -----------------
;;; -----------------------------------------------

(defn query
  "Executes a search query."
  ([]
   (query nil nil nil))

  ([body]
   (query body nil nil))

  ([body index]
   (query body index nil))

  ([body index type]
   {:url    (helper/file index type "_search")
    :method :get
    :body   body}))


(defn template
  "Allows use of mustache language to pre-render search requests."
  [body]
  {:url    "template"
   :body   body
   :method :get})


(defn shards
  "Returns the indicies and shards that a search request would be
  executed against."
  ([index]
   (shards index nil))

  ([index type]
   {:url    (helper/file index type "_search_shards")
    :method :get}))


(defn suggest
  "Suggests similar looking terms based on a provided text by using
  a suggester."
  [body]
  {:url    "_suggest"
   :method :get
   :body   body})


(defn count
  "Executes a query to get the number of matches for that query."
  ([index type]
    (count nil index type))

  ([body index type]
   {:url    (helper/file index type "_count")
    :method :get
    :body   body}))


(defn validate
  "Validates a potentially expensive query without executing it."
  ([]
    (validate nil nil nil))

  ([body]
   (validate body nil nil))

  ([body index]
   (validate body index nil))

  ([body index type]
   {:url (helper/file index type "_validate/query")
    :method :get
    :body body}))


(defn explain
  "Computes a score explanation for a query and a specific document."
  [body index type id]
  {:url    (helper/file index type id "_explain")
   :method :get
   :body   body})
