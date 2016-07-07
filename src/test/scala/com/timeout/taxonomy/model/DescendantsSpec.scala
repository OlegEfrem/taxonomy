package com.timeout.taxonomy.model

import com.timeout.taxonomy.helpers.TestData
import org.scalatest.{Matchers, WordSpec}

class DescendantsSpec extends WordSpec with Matchers with TestData {

  "descendantsAsNodes" should {

    "map no descendants node to empty list" in {
      val node = createNode()
      node.descendantsAsNodes shouldBe List()
    }

    "map two descendants node to two nodes list" in {
      val decendantsNodes = List(randomNode, randomNode)
      val node = createNode(descendants = Some(Descendants(decendantsNodes)))
      node.descendantsAsNodes shouldBe decendantsNodes
    }

  }

}
