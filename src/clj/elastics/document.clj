(ns elastics.document
  (:require [elastics.helper :as helper])
  (:refer-clojure :exclude [get update]))


(defn index
  "Adds or updates a document in a specific index."
  ([body index type]
   {:url    (helper/file index type)
    :body   body
    :method :post})

  ([body index type id]
   {:url    (helper/file index type id)
    :body   body
    :method :put}))


(defn get
  "Gets a document from a specific index based on its id."
  [index type id]
  {:url    (helper/file index type id)
   :method :get})


(defn delete
  "Gets a document from a specific index based on its id."
  [index type id]
  {:url    (helper/file index type id)
   :method :delete})


(defn update-with-script
  "Updates a document based on a provided script."
  [body index type id]
  {:url    (helper/file index type id "_update")
   :body   body
   :method :post})


(defn mget
  "Gets multiple documents based on body, index, or type."
  ([body]
   (mget body nil nil))

  ([body index]
   (mget body index nil))

  ([body index type]
   {:url    (helper/file index type "_mget")
    :body   body
    :method :get}))


(defn term-vectors
  "Returns information and statistics on terms in the fields
  of a particular document."
  [index type id]
  {:url    (helper/file index type id "_termvectors")
   :method :get})


(defn mterm-vectors
  "Gets multiple term vectors at once."
  ([body]
   (mterm-vectors body nil nil))

  ([body index]
   (mterm-vectors body index nil))

  ([body index type]
   {:url    (helper/file index type "_mtermvectors")
    :body   body
    :method :get}))
