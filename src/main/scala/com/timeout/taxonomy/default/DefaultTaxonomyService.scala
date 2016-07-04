package com.timeout.taxonomy.default

import com.timeout.taxonomy.TaxonomyService
import com.timeout.taxonomy.model.{Descendants, Node, Tag}
import com.timeout.taxonomy.repository.TaxonomyRepository

class DefaultTaxonomyService(private val taxonomyRepository: TaxonomyRepository) extends TaxonomyService {

  override def getNodes(nodeId: String): Set[Node] = {
    Set()
  }

  override def getNodeDescendants(parentNodeId: String): Set[Descendants] = {
    Set()
  }

  override def getNodesWithTag(tagId: String): Set[Node] = {
    Set()
  }

}
