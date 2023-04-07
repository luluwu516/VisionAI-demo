# Vision AI Demo

The program utilizes the Vision API to assign labels to images, classify them, and then export the results as an Excel file with Java.

![VisionAI_demo](https://user-images.githubusercontent.com/98475122/230657521-6e371fe3-77c3-41f2-9d83-e5a1fab7138d.png)

## Problem-Solving

I learned Java from the [Hahow](https://hahow.in/courses/5892e48a5f188a07007f7013/main), an awesome online learning and teaching platform in Taiwan. 
This is my first project with Google Cloud Platform (GCP).

The course is excellent, but it was created in 2017, so some of the techniques are outdated. Thankfully, Google provides a detailed document that 
I read through in order to fix the issues. After going through all the documents, I was able to get it to work. However, I still encountered an error: 
"Bad image data." 

I searched for a solution online, but couldn't find any references, so I debugged it myself. It turned out that the error was caused by 
a hidden ".DS_store" file, which stores custom attributes for macOS and was inadvertently included in my input folder. To fix this, I wrote a 
function called "removeItemFromArray()" to remove ".DS_store" from the input folder. And it finally works!

If you want to try it yourself, feel free to ask me questions!

<hr>

### The Hidden File

As you can see, there are four pictures in the Finder. Users can not see the ".DS_store" file in the Finder.
<img width="600" alt="Finder" src="https://user-images.githubusercontent.com/98475122/230641282-a9c7733c-dfff-4fa7-9c9b-521c58dc989a.png">

However, if you check the finder's "Get Info," we can see five files in the Finder.

<img width="405" alt="Finder_info" src="https://user-images.githubusercontent.com/98475122/230641286-cef5de32-9c77-45ba-8d2a-2b7be39772fc.png">

<hr>

### What is Vision API?

> Vision API offers powerful pre-trained machine learning models through REST and RPC APIs. Assign labels to images and quickly classify them into 
millions of predefined categories. Detect objects, read printed and handwritten text, and build valuable metadata into your image catalog.

It's a Google Cloud Product. You can try it [here](https://cloud.google.com/vision).

<img width="795" alt="GCP_demo" src="https://user-images.githubusercontent.com/98475122/230645775-87b57cf1-0eea-4dfa-9a5c-459e391b9001.png">

