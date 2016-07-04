package com.timeout.taxonomy

import com.timeout.taxonomy.default.DefaultTaxonomyService
import com.timeout.taxonomy.helpers.TestData
import com.timeout.taxonomy.repository.TaxonomyRepository
import org.scalatest.mock.MockitoSugar
import org.scalatest.{Matchers, WordSpec}
import org.mockito.Mockito._

class TaxonomyServiceSpec extends WordSpec with Matchers with MockitoSugar with TestData {
  val repository = mock[TaxonomyRepository]
  val taxonomyService: TaxonomyService = new DefaultTaxonomyService(repository)

  "getNode" should {

    "return one node if there is only one with given name" in {
      val node = createNode(id = oneNodeId)
      when(repository.tree()).thenReturn(Set(node, randomNode))
      val result = taxonomyService.getNode(oneNodeId)
      result shouldBe Set(node)
    }

    "return multiple nodes if there are multiple nodes with same name in different branches" in {
      val nodes = createTwoSameNodeIdInDifferentBranches()
      when(repository.tree()).thenReturn(nodes.+(randomNode))
      val result = taxonomyService.getNode(twoSameNodeIdInDifferentBranches)
      result.size shouldBe 2
    }

    "return an empty Set if there is no node with given name" in {
      when(repository.tree()).thenReturn(Set(randomNode, randomNode))
      val result = taxonomyService.getNode(notExistingId)
      result shouldBe empty
    }

    "return only the highest node in the hierarchy of a given branch" in {
      val node = createTwoSameNodeIdInSameBranch()
      when(repository.tree()).thenReturn(Set(node, randomNode))
      val result = taxonomyService.getNode(twoSameNodeIdInSameBranch)
      result.size shouldBe 1
    }

  }

  "getNodeDescendants" should {

    "return one Descendants entry if there is only one node with given name" in {
      val node = createNode(id = oneNodeId)
      when(repository.tree()).thenReturn(Set(node, randomNode))
      val result = taxonomyService.getNodeDescendants(oneNodeId)
      result shouldBe Set(node.descendants)
    }

    "return multiple Descendats entries if there are multiple Nodes with same name in different branches" in {
      val nodes = createTwoSameNodeIdInDifferentBranches()
      when(repository.tree()).thenReturn(nodes.+(randomNode))
      val result = taxonomyService.getNodeDescendants(twoSameNodeIdInDifferentBranches)
      result.size shouldBe 2
    }

    "return an empty Set if there is no Node with given name" in {
      when(repository.tree()).thenReturn(Set(randomNode, randomNode))
      val result = taxonomyService.getNodeDescendants(notExistingId)
      result shouldBe empty
    }

    "return only the highest Descendants in the hierarchy of a given branch" in {
      val node = createTwoSameNodeIdInSameBranch()
      when(repository.tree()).thenReturn(Set(node, randomNode))
      val result = taxonomyService.getNodeDescendants(twoSameNodeIdInSameBranch)
      result.size shouldBe 1
    }

  }

  "getNodesWithTag" should {

    "return one node if there is only one with given Tag" in {
      val node = createNode(tagId = oneTagId)
      when(repository.tree()).thenReturn(Set(node, randomNode))
      val result = taxonomyService.getNodesWithTag(oneTagId)
      result shouldBe Set(node)
    }

    "return multiple nodes if there are multiple with same Tag in different branches" in {
      val nodes = createTwoSameTagInDifferentBranches()
      when(repository.tree()).thenReturn(nodes.+(randomNode))
      val result = taxonomyService.getNodesWithTag(twoSameTagInDifferentBranches)
      result.size shouldBe 2
    }

    "return an empty Set if there is no node with given Tag" in {
      when(repository.tree()).thenReturn(Set(randomNode, randomNode))
      val result = taxonomyService.getNodesWithTag(notExistingId)
      result shouldBe empty
    }

    "return all the nodes with given Tag in the hierarchy of a given branch" in {
      val node = createTwoSameTagIdInSameBranch()
      when(repository.tree()).thenReturn(Set(node, randomNode))
      val result = taxonomyService.getNodesWithTag(twoSameTagIdInSameBranch)
      result.size shouldBe 2
    }

  }

}