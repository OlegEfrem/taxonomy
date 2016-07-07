package com.timeout.taxonomy.default

import com.timeout.taxonomy.TaxonomyService
import com.timeout.taxonomy.model.Node
import com.timeout.taxonomy.repository.TaxonomyRepository

import scala.annotation.tailrec

class DefaultTaxonomyService(private val taxonomyRepository: TaxonomyRepository) extends TaxonomyService {

  override def getNodes(nodeId: String): Seq[Node] = {
    @tailrec
    def extractMatching(nodes: List[Node], prevResult: Seq[Node] = Seq()): Seq[Node] = {
      nodes match {
        case Nil => prevResult
        case head :: tail => head.id == nodeId match {
          case true => extractMatching(tail, prevResult.:+(head))
          case false => extractMatching(tail.:::(head.descendantsAsNodes), prevResult)
        }
      }
    }
    extractMatching(taxonomyRepository.tree())
  }

  override def getNodesWithTag(tagId: String): Seq[Node] = {
    def nextResult(currentNode: Node, prevResult: Seq[Node]): Seq[Node] =
      currentNode.tag.id == tagId match {
        case true => prevResult.:+(currentNode)
        case false => prevResult
      }
    @tailrec
    def extractMatching(nodes: List[Node], prevResult: Seq[Node] = Seq()): Seq[Node] = {
      nodes match {
        case Nil => prevResult
        case head :: tail => extractMatching(head.descendantsAsNodes.:::(tail), nextResult(head, prevResult))
      }
    }
    extractMatching(taxonomyRepository.tree())
  }

}
