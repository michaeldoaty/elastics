(ns elastics.cluster-test
  (:require [clojure.test :refer :all]
            [elastics.cluster :as cluster]))



(def body {:greet "hello"})



(deftest health-test
  (testing "without index"
    (is (= (cluster/health)
           {:method :get
            :url    "_cluster/health"})))

  (testing "with index"
    (is (= (cluster/health "twitter")
           {:method :get
            :url    "_cluster/health/twitter"}))))



(deftest state-test
  (testing "without index"
    (is (= (cluster/state)
           {:method :get
            :url    "_cluster/state"})))

  (testing "with index"
    (is (= (cluster/state "twitter" "_all")
           {:method :get
            :url    "_cluster/state/_all/twitter"}))))



(deftest stats-test
  (is (= (cluster/stats)
         {:method :get
          :url    "_cluster/stats"})))



(deftest pending-tasks-test
  (is (= (cluster/pending-tasks)
         {:method :get
          :url    "_cluster/pending_tasks"})))



(deftest reroute-test
  (is (= (cluster/reroute body)
         {:method :post
          :url    "_cluster/reroute"
          :body   body})))



(deftest update-settings-test
  (is (= (cluster/update-settings body)
         {:method :put
          :url    "_cluster/settings"
          :body   body})))



(deftest node-stats-test
  (testing "with no arguments"
    (is (= (cluster/node-stats)
           {:method :get
            :url    "_nodes/stats"})))

  (testing "with stats"
    (is (= (cluster/node-stats "os,process")
           {:method :get
            :url    "_nodes/stats/os,process"})))

  (testing "stats and nodes"
    (is (= (cluster/node-stats "os" "node1")
           {:method :get
            :url    "_nodes/stats/node1/os"}))))



(deftest node-info-test
  (testing "with no arguments"
    (is (= (cluster/node-info)
           {:method :get
            :url    "_nodes"})))

  (testing "with attributes"
    (is (= (cluster/node-info "jvm,process")
           {:method :get
            :url    "_nodes/jvm,process"})))

  (testing "stats and nodes"
    (is (= (cluster/node-info "jvm" "node1")
           {:method :get
            :url    "_nodes/node1/jvm"}))))



(deftest node-hot-threads-test
  (testing "with no arguments"
    (is (= (cluster/node-hot-threads)
           {:method :get
            :url    "_nodes/hot_threads"})))

  (testing "with node"
    (is (= (cluster/node-hot-threads "nodeId")
           {:method :get
            :url    "_nodes/nodeId/hot_threads"}))))
