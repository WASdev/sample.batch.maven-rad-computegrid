# An example approach for working with Maven and RAD Batch tools for WebSphere Application Server traditional Version 8.5 & 9.0, and Compute Grid Version 8.0.
---

## Introduction

You want to develop in RAD, where incremental changes are continually compiled and published to a running WebSphere Application Server (WAS) server, but you also want to take advantage of Maven repositories and Maven's ecosystem of DevOps tools.

This document shows one sample approach.   It is not intended to be an official recommendation or to cover every single case.

## Sample Use Cases

We actually show a pair of sample approaches.  

### 1. Start from RAD

If you're already familiar with the batch development tools in RAD, and want to consider how to make them fit into a Maven build, you can follow along with this set of instructions.

### 2. Start from a Maven archetype

This sequence of instructions uses a sample Maven archetype (a set of related module templates) designed to integrate well into the RAD batch tooling.

It starts with an already-provided template defining a simple one-step job performing simple text manipulation.   The instructions have you create and then run your own variant of this sample, leading you to the point where you can work with your own application in RAD and take advantage of all its development assistance.


## What's interesting here, and what isn't

It's important to realize that the takeaway from this project, however, isn't the sample archetype itself.   

The useful aspect is the set of dependencies used in each module, the configuration of the *maven-ear-plugin* and *maven-ejb-plugin*, the Maven call to the PGCPackager, the instructions detailing which commands to issue in RAD (such as the EJB deploy) and in what order, etc.  

Though the archetype will only create the simple one-step text file processing job each time, the patterns and instructions behind it should be useful for other applications.

## Suggested path through doc

If you're not at all sure where to start from, the latter **Start from a Maven archetype** could be a good choice.   That's because the instructions are a bit simpler to follow, and they are more completely-detailed in this documentation.

But there is more IBM-provided documentation available on starting from RAD and creating a new Batch Project, etc. from that angle, so if you've already worked with the RAD batch tools, the "start from RAD" approach might better build on what you've already done. 

### Meet in middle?

The instructions were not designed specifically in order to end up with the exact same POMs no matter whether you started from RAD or from Maven.  Instead they were designed to be illustrative and convenient from each individual angle.

If you are interested in extending these approaches and building your own archetypes, it probably would help to read through both approaches.

### How do I develop my own archetype for batch applications?

In letting you bootstrap with the sample **textfile-io-archetype** provided here we skip over the question of "how did this archetype (template) get developed" and "how can I create my own".    

By showing you how to create a project from an existing archetype, and showing you how to convert an existing RAD project to Maven (the "Start from RAD" approach), we are showing the key pieces of what the overall Maven project needs to look like.

The final piece of the puzzle is the **archetype:create-from-project** goal which allows you to easily create a new archetype from an existing project, e.g. see [here](http://maven.apache.org/archetype/maven-archetype-plugin/examples/create-multi-module-project.html).

## Prerequisites

1. Rational Application Developer (RAD) with:
	* The Modern Batch tools
	* WebSphere Application Server V9.0 tools
	* m2e - This is the Eclipse plugin for working with Maven within Eclipse/RAD
1. A WebSphere Application Server V9.0 installation (or V8.5 with slight modifications, not shown) with the **Embeddable EJB container** optional feature included as part of the installation.
	
	
**Note:** These instructions were tested with RAD Version 9.6, and Version 9.0.0.3. 

## Next step

* [Next article](/docs/common-setup.md) - Initialize your local Maven repository to work with these sample instructions

# Notice

© Copyright IBM Corporation 2017.

# License

    /*
     * Copyright 2017 International Business Machines Corp.
     * 
     * See the NOTICE file distributed with this work for additional information
     * regarding copyright ownership. Licensed under the Apache License, 
     * Version 2.0 (the "License"); you may not use this file except in compliance
     * with the License. You may obtain a copy of the License at
     * 
     *   http://www.apache.org/licenses/LICENSE-2.0
     * 
     * Unless required by applicable law or agreed to in writing, software
     * distributed under the License is distributed on an "AS IS" BASIS,
     * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
     * See the License for the specific language governing permissions and
     * limitations under the License.
     */


