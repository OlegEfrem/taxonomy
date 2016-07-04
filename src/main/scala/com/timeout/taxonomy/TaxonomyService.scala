package com.timeout.taxonomy

import com.timeout.taxonomy.model.{Descendants, Node, Tag}

/**
  * Service to retrieve taxonomy nodes
  */
trait TaxonomyService {
  /**
    * Gets a Node by it's id, if no Node with such id found returns an empty Set, if multiple nodes with same id are found, it returns multiple nodes.
    * It will only return the highest Node in a given branch.
    *
    */
  def getNodes(nodeId: String): Set[Node]

  /**
    * Get descendants by Node id, if no Node with such id found returns and empty Set, if multiple nodes with same id found, it returns multiple Descendants.
    * It uses getNode to retrieve parent nodes, then maps the results to Descendants of the found nodes.
    */
  def getNodeDescendants(parentNodeId: String): Set[Descendants]

  /**
    * Gets nodes with specific Tag, if no node with such tag found returns an empty Set, if multiple nodes with same Tag found, it returns multiple nodes.
    * It will return all nodes in a given branch that has the input Tag.
    */
  def getNodesWithTag(tagId: String): Set[Node]

}