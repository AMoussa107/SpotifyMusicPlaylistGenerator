# Fibonacci-Heap-CS136-Final-Project
![alt text](https://github.com/AMoussa107/Fibonacci-Heap-CS136-Final-Project/assets/150184302/08cb2218-5c92-48bb-b587-f6b5216890c1)

## Basic Overview
This program is a music playlist generator that will give you a list of songs that best match your musical preferences on Spotify through efficient priority queue implementation that uses the Fibonacci Heap Data Structure.

## Fibonacci Heap: An Overview
A Fibonacci Heap is a data structure typically used for priority queue operations. Fibonacci Heaps are a collection of min-Heap-ordered trees, a tree that maintains the min-heap property. The min-heap property means that for every child node c and parent node p, c.key ≥ p.key. This collection has the following properties: 
- Each node in the tree has four pointers: the parent node, a child, the left node, the right node.
- All siblings are linked together through a doubly linked list.
- The root list of the collection of trees is connected through a doubly linked list.
- Each node has a degree value (number of children), and a boolean value marked (always false unless the node loses a child)
- We also keep a pointer to the minimum value in the root list.

The picture below describes the above structure: 
![alt text](https://github.com/AMoussa107/Fibonacci-Heap-CS136-Final-Project/assets/150184302/d8319681-39d8-451d-83a1-677d6bf41b1f)

## Fibonacci Heap: Operations
Here's a list of elementary operations implemented in our version of the Fibonacci Heap:
| Operation | Description     |
|----|----------|
|insert | We insert a new node to the heap by adding it to the root list|
| min | Returns the minimum value from the root list | 
| union  | Puts the root lists of two Fibonacci heaps together and updates pointers to maintain heap properties | 
| extractMin  | Removes and returns the minimum node from the heap the consolidates the remaining trees to maintain heap structure|
| decreaseKey | Updates the priority value (key) of a certain node and performs necessary cuts and merges to maintain the min-heap properties within the tree |
|delete| Decreases the key of the specified node to Negative infinity and then extracts Minimum value to remove it from the Heap and maintain structure|

## Fibonacci Heap: Analysis
The Fibonacci Heap structure does a great job of minimizing the complexity of the number operations needed to implement and use a priority queue. This is achieved through laziness that leads to amortized constant-time complexity. This structure only does work when it's necessary, and does it in a way that would make future operations easier. The insert method is a great example of what we call a "lazy" operation, wher instead of trying to add a new node to one of the existing heap-trees and placing it correctly, it just adds this new node to the root list creating a new heap-tree of order 0. The time complexity of our Fibonacci Heap operations is listed in the table below:
| Operation | Fibonacci Heap  (amortized)   | Binary Heap|
|----|----------| --------- |
|insert | O(1) | O(log(n))|
| min | O(1) | O(1)|
| union  | O(1) | O(n)|
| extractMin  |O(log(n)| O(log(n))|
| decreaseKey | O(1) | O(log(n))|
|delete|O(log(n)) | O(log(n))|



## Application
We are making use of a priority queue ADT (implemented with the Fibonacci Heap) to generate (queue) songs from our data set into a playlist. The key of each song node that's being added to the heap is determined through input from the user of their preferences and then scoring each song based on how well its attributes match the user preferred attributes. The input from the user is taken through a graphical interface with sliders, drop down menus, checkboxes, and textboxes, and then after submitting their preferences, we extract the first n minimum values from the heap where n is the number of songs the user wants in the playlist. This is how the interface looks like: 

<img width="800" alt="Screenshot 2024-05-14 at 2 06 06 AM" src="https://github.com/AMoussa107/Fibonacci-Heap-CS136-Final-Project/assets/150184302/b985d726-7e76-43b3-9347-5ecebaf1df68">

This interface was implemented using the Javax.swing and Java.awt packages used in lab 2. Input is taken from the user through the attribute textboxes, dropdown menus, sliders, and priority sliders. Each slider in the interface can be set to a value between 0 (min) and 100 (max). The meaning of the value of attribute sliders will be explained when we come to the data set demonstration. The Priority slider value determines the weight of matching each attribute to our list of songs, which is used to calculate the scores that we will use to insert the songs into the priority queue to generate the playlist. The user can make certain attributes contribute more, or less, to the generation of the playlist according to how important they think it is. The user can choose to include or not include explicit songs in their playlist through a checkbox. After selecting their preferences, the user can then add the number of songs they want on the playlist and click the "Generate Playlist" button. After clicking the button, the playlist is displayed in a window with each track's title and list of artists as follows: 

<img width="798" alt="Screenshot 2024-05-14 at 2 24 49 AM" src="https://github.com/AMoussa107/Fibonacci-Heap-CS136-Final-Project/assets/150184302/922e43c1-aeaa-426d-8e4e-9b984071be0f">

## Data Set:
For playlist generation, we ingest songs from a csv data set of spotify tracks that has each song with its attributes. Each track has a Spotify id attributes that are separated by commas. The first three lines in the data set look like this: 

```
0,5SuOikwiRyPMVoIQDJUgSV,Gen Hoshino,Comedy,Comedy,73,3.844431796,FALSE,67.6,46.1,1,13.492,0,14.3,3.22,0.000101,35.8,71.5,36.03155738,4,acoustic
1,4qPNDBW1i3p13qLCt0Ki3A,Ben Woodward,Ghost (Acoustic),Ghost - Acoustic,55,2.493499003,FALSE,42,16.6,1,34.47,1,7.63,92.4,0.000556,10.1,26.7,31.75778688,4,acoustic
2,1iJBSr7s7jYXzM8EGcbK5b,Ingrid Michaelson;ZAYN,To Begin Again,To Begin Again,57,3.513765261,FALSE,43.8,35.9,0,19.468,1,5.57,21,0,11.7,12,31.28360656,4,acoustic

```
The values in each line in order are: track_number,track_id,artists,album_name,track_name,popularity,duration_mins,explicit,danceability,energy,key,loudness,mode,speechiness,acousticness,instrumentalness,liveness,valence,tempo,time_signature,track_genre

Here's a breakdown of what the used attributes in our application mean: 

| Attribute | Definition | 
|----|----------|
|track_id| The Spotify ID for the track |
|artists|The list of artists' names who performed the track. If there is more than one artist, they are separated by a ';'|
|track_name|Name of the track|
|popularity|The popularity of a track is a value between 0 and 100, with 100 being the most popular|
|duration_mins|The track's duration in minutes|
|explicit|Whether or not the track has explicit lyrics (true = yes it does; false = no it does not OR unknown)|
|danceability|Danceability describes how suitable a track is for dancing based on a combination of musical elements including tempo, rhythm stability, beat strength, and overall regularity. A value of 0 is least danceable and 100 is most danceable|
|energy|Energy is a measure from 0 to 100 and represents a perceptual measure of intensity and activity. Typically, energetic tracks feel fast, loud, and noisy. For example, death metal has high energy, while a Bach prelude scores low on the scale|
|loudness|The overall loudness of a track in decibels (dB)|
|speechiness|Speechiness detects the presence of spoken words in a track. The more exclusively speech-like the recording (e.g. talk show, audio book, poetry), the closer to 100 the attribute value|
|acousticness |A confidence measure from 0 to 100 of whether the track is acoustic. 100 represents high confidence the track is acoustic|
|instrumentalness |Predicts whether a track contains no vocals. "Ooh" and "aah" sounds are treated as instrumental in this context. Rap or spoken word tracks are clearly "vocal". The closer the instrumentalness value is to 100, the greater likelihood the track contains no vocal content|
|liveness|Detects the presence of an audience in the recording. Higher liveness values represent an increased probability that the track was performed live|
|valence|A measure from 0 to 100 describing the musical positiveness conveyed by a track. Tracks with high valence sound more positive (e.g. happy, cheerful, euphoric), while tracks with low valence sound more negative (e.g. sad, depressed, angry)|
|tempo |The overall estimated tempo of a track in beats per minute (BPM). In musical terminology, tempo is the speed or pace of a given piece and derives directly from the average beat duration|
|track_genre|The genre in which the track belongs out of more than 125 genres list|








