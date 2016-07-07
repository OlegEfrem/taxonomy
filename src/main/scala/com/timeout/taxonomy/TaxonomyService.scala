package com.timeout.taxonomy

import com.timeout.taxonomy.model.{Descendants, Node, Tag}

/**
  * Service to retrieve taxonomy nodes
  */
trait TaxonomyService {
  /**
    * Gets a Node by it's id, if no Node with such id found returns an empty Seq, if multiple nodes with same id are found, it returns multiple nodes.
    * It will only return the highest Node in a given branch.
    *
    */
  def getNodes(nodeId: String): Seq[Node]

  /**
    * Get descendants by Node id, if no Node with such id found returns and empty Seq, if multiple nodes with same id found, it returns multiple Descendants.
    * It uses [[TaxonomyService.getNodes()]] to retrieve parent nodes, then maps the results to Descendants of the found nodes.
    */
  def getNodeDescendants(parentNodeId: String): Seq[Descendants] =
    getNodes(parentNodeId).collect { case node if node.descendants.isDefined => node.descendants.get }

  /**
    * Gets nodes with specific Tag, if no node with such tag found returns an empty Seq, if multiple nodes with same Tag found, it returns multiple nodes.
    * It will return all nodes with the input Tag in a given branch.
    */
  def getNodesWithTag(tagId: String): Seq[Node]

}