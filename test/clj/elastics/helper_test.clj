(ns elastics.helper-test
  (:require [clojure.test :refer :all]
            [elastics.helper :as helper]))


(deftest file-test
  (testing "removing of nil"
    (is (= (helper/file "file1" nil "file2")
           "file1/file2")))

  (testing "nil"
    (is (= (helper/file nil)
           "")))

  (testing "empty string"
    (is (= (helper/file "")
           ""))))


(deftest create-url-test
  (is (= (helper/create-url "http://localhost:9200" "twitter/tweet")
         "http://localhost:9200/twitter/tweet")))

