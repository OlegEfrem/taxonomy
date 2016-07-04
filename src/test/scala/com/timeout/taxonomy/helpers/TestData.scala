package com.timeout.taxonomy.helpers

import com.timeout.taxonomy.model.{Descendants, Node, Tag}
import scala.util.Random

trait TestData {
  val notExistingId = "notExistingId"
  val oneNodeId = "oneNodeId"
  val twoSameNodeIdInSameBranch = "twoSameIdNodeInSameBranch"
  val twoSameNodeIdInDifferentBranches = "twoSameIdNodeInDifferentBranches"
  val oneTagId = "oneTagId"
  val twoSameTagIdInSameBranch = "twoSameIdNodeInSameBranch"
  val twoSameTagInDifferentBranches = "twoSameIdNodeInDifferentBranches"

  def randomNode = createNode(randomString)

  def createTwoSameNodeIdInSameBranch(): Node = {
    createNode(
      id = twoSameNodeIdInSameBranch,
      descendants = Some(
        Descendants(Seq(
          createNode(
            id = twoSameNodeIdInSameBranch,
            parent = Some(createNode(id = twoSameNodeIdInSameBranch)))
        )))
    )
  }

  def createTwoSameNodeIdInDifferentBranches(): Set[Node] = {
    val randomNodeId = randomString
    Set(
      createNode(
        id = twoSameNodeIdInSameBranch,
        descendants = Some(Descendants(Seq(randomNode)))
      ),
      createNode(
        id = randomNodeId,
        descendants = Some(
          Descendants(Seq(
            createNode(
              id = twoSameNodeIdInSameBranch,
              parent = Some(createNode(id = randomNodeId)))
          )))
      )
    )
  }

  def createTwoSameTagIdInSameBranch(): Node = {
    createNode(
      tagId = twoSameNodeIdInSameBranch,
      descendants = Some(
        Descendants(Seq(
          createNode(
            tagId = twoSameNodeIdInSameBranch,
            parent = Some(createNode(id = twoSameNodeIdInSameBranch)))
        )))
    )
  }

  def createTwoSameTagInDifferentBranches(): Set[Node] = {
    val randomNodeId = randomString
    Set(
      createNode(
        tagId = twoSameNodeIdInSameBranch,
        descendants = Some(Descendants(Seq(randomNode)))
      ),
      createNode(
        tagId = randomNodeId,
        descendants = Some(
          Descendants(Seq(
            createNode(
              tagId = twoSameNodeIdInSameBranch,
              parent = Some(createNode(id = randomNodeId)))
          )))
      )
    )
  }

  def createNode(id: String = randomString, tagId: String = randomString, parent: Option[Node] = None, descendants: Option[Descendants] = None) =
    Node(
      id = id,
      tag = Tag(id = tagId, translations = Seq()),
      parent = parent,
      descendants = descendants
    )

  private def randomString = Random.nextString(10)

}
