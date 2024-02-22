import android.os.Build
import androidx.annotation.RequiresApi


@RequiresApi(Build.VERSION_CODES.O)
fun main() {
//--------------------------------------------
    //Opgave 1
    val article = ArrayList<Article>()

    article.add(Article("Christine", "Drage-elskeren"))
    article.add(Article("Birk", "Bodega-mandag"))
    article.add(Article("Natazja", "Work"))
    article.add(Article("Rasmsus", "Big-tower1"))
    article.add(Article("Christian", "Big-tower2"))

    article.forEach { println(it) }

    println()


    //--------------------------------------------
    //Opgave 2

    val sandwich = Sandwich(cheese = false, meat = true, soda = false, bread = true, price = 45)
    val pizza = Pizza(cheese = true, meat = true, soda = false, bread = false, size = "L")
    val burger = Burger(cheese = true, meat = true, soda = true, bread = true, burgerAmount = 30)

    val fastFood = arrayOf<FastFood>(sandwich, pizza, burger)
    fastFood.forEach { food ->
        when (food) {
            is Sandwich -> {
                println("Sandwich")
                println("Cheese: ${food.cheese}, Meat: ${food.meat}, Soda: ${food.soda}, Bread: ${food.bread}, Price: ${food.price}")
                println()
            }
            is Pizza -> {
                println("Pizza")
                println("Cheese: ${food.cheese}, Meat: ${food.meat}, Soda: ${food.soda}, Bread: ${food.bread}, Size: ${food.size}")
                println()
            }
            is Burger -> {
                println("Burger")
                println("Cheese: ${food.cheese}, Meat: ${food.meat}, Soda: ${food.soda}, Bread: ${food.bread}, Amount: ${food.burgerAmount}")
                println()
            }

        }
    }
    println()
    //--------------------------------------------

    //Opgave 3

    val car1 = Car()
    val car2 = Car()

    car1.changeGear(4)
    car1.speedUp(100)
    car1.applyBrakes(20)
println()
    car2.changeGear(2)
    car2.speedUp(70)
    car2.applyBrakes(20)

println()

    //--------------------------------------------
    //Opgave 4
    val post1 = RedditPost("Lucas", "SBH")
    post1.downVotes = 4
    post1.upVotes = 45
    println(post1)

    val post2 = RedditPost("Laurids", "KU")
    post2.downVotes = 2
    post2.upVotes = 10
    println(post2)

    val post3 = RedditPost("Hadi", "DTU")
    post3.downVotes = 5
    post3.upVotes = 5
    println(post3)

    val postsSortedByUpvotes = RedditPost.sortByUpvotes(listOf(post1, post2, post3))
    println("\nPosts sorted by upvotes:")
    postsSortedByUpvotes.forEach { println(it) }

    val postsSortedByDownvotes = RedditPost.sortByDownvotes(listOf(post1, post2, post3))
    println("\nPosts sorted by downvotes:")
    postsSortedByDownvotes.forEach { println(it) }



    println()

    //--------------------------------------------
    //Opgave 4.5

    val frontPage = RedditFrontPage()

    //Adding some posts
    frontPage.addpost(RedditPost("Lille-fredag", "Lucas"))
    frontPage.addpost(RedditPost("Title", "Laurids"))
    frontPage.addpost(RedditPost("Fodbold", "Hadi"))
    frontPage.addpost(RedditPost("Kløvermarken", "Mads"))


    //Print before delete
    println("Front page before deleted post")
    frontPage.printFrontPage()

println()
    //Delete a post by index
    frontPage.deletePost(2)


    //Print after deleted post
    println("After deleted post")
    frontPage.printFrontPage()

    println()

//--------------------------------------------

    //opgave 5
    val words = listOf("apple", "banana", "apple", "orange", "banana", "apple", "orange", "banana", "apple","banana", "apple", "orange", "banana","banana", "apple", "orange", "apple", "orange", "banana")
    frequency(words)
}







//--------------------------------------------

//OPGAVE 1
//Write a class: Article
//An article has an author and a title
//Create 5 articles, add them into an ArrayList and print them by overriding the .toString() method

class Article(val author: String, val title: String) {
    override fun toString(): String {
        return "Author: $author Title: $title"
    }
}

//--------------------------------------------

//OPGAVE 2
//Create an interface called FastFood (with appropriate methods) and create a Sandwich class,
// a Pizza class and a class you decide that implements the FastFood interface.
//Add some different Fastfood objects to an array. Now iterate through that
// array and use some of the methods you have created above.

interface FastFood {
    val cheese: Boolean
    val meat: Boolean
    val soda: Boolean
    val bread: Boolean
}

class Sandwich(
    override val cheese: Boolean,
    override val meat: Boolean,
    override val soda: Boolean,
    override val bread: Boolean,
    val price: Int
): FastFood

class Pizza(
    override val cheese: Boolean,
    override val meat: Boolean,
    override val soda: Boolean,
    override val bread: Boolean,
    val size: String
): FastFood

class Burger(
    override val cheese: Boolean,
    override val meat: Boolean,
    override val soda: Boolean,
    override val bread: Boolean,
    val burgerAmount: Int
): FastFood


//--------------------------------------------

//OPGAVE 3
//Create a class that implements the following interface.
//interface Vehicle {
//    changeGear(int a);
//    speedUp(int a);
//    applyBrakes(int a);
//}
// Now create two objects using the class created


interface Vehicle {
    val changeGear: Int
    val speedUp: Int
    val applyBrakes: Int
}

class Car: Vehicle {
    var currentGear: Int = 0
    var currentSpeed: Int = 0

   fun changeGear(gear: Int){
       currentGear = gear
       println("Car changed gear to $gear")
   }
    fun speedUp(speed: Int) {
        currentSpeed += speed
        println("Speeding up with $speed km/h. Current speed now $currentSpeed")
    }
    fun applyBrakes(brake: Int){
        currentSpeed -= brake
        println("Using brakes. Speed reduced by $brake. Current speed now $currentSpeed")
    }

    override val changeGear: Int
        get() = TODO("Not yet implemented")
    override val speedUp: Int
        get() = TODO("Not yet implemented")
    override val applyBrakes: Int
        get() = TODO("Not yet implemented")
}


//--------------------------------------------

//OPGAVE 4
//Write a class: RedditPost
//A Redditpost has :
//A date of which is has been posted
//An author
//A balance of upvotes / downvotes
//A Title
//When a new instance of RedditPost is instantiated:
//The current date will be generated.
//The balance of upvotes and downvotes starts at 1.
//The title and author has to be provided by the constructor.
//Ensure all attributes are private, but accesible by getters & setters.
//Implement functionality such that redditposts can be sorted by upvotes/downvotes



class RedditPost(val author: String, val title: String) {
    private val date: String
    private var upvotes: Int
    private var downvotes: Int

    init {
        val sdf = SimpleDateFormat("dd-MM-yyyy HH-mm-ss")
        date = sdf.format(Date())
        upvotes = 1
        downvotes = 1
    }

    var upVotes: Int
        get() = upvotes
        set(value) {
            upvotes = value
        }

    var downVotes: Int
        get() = downvotes
        set(value) {
            downvotes = value
        }

    companion object {
        fun sortByUpvotes(posts: List<RedditPost>): List<RedditPost> {
            return posts.sortedByDescending { it.upVotes }
        }

        fun sortByDownvotes(posts: List<RedditPost>): List<RedditPost> {
            return posts.sortedByDescending { it.downVotes }
        }
    }

    override fun toString(): String {
        return "Author: $author, Title: $title, Timestamp: $date, Upvotes: $upvotes, Downvotes: $downvotes";
    }
}


//--------------------------------------------

//Opgave 4.5
//Write a class: RedditFrontPage
//The RedditFrontPage has:
//A List of all RedditPosts
//A method in RedditFrontPage deletes a RedditPost from the list, by its index number

class RedditPost(val title: String, val author: String) {
    override fun toString(): String {
        return "Title: $title, Author: $author"
    }
}


class RedditFrontPage(
    val redditPost: MutableList<RedditPost> = mutableListOf()) {

    //Add a Redditpost to front page
    fun addpost(post: RedditPost){
        redditPost.add(post)
    }

    //Delete a Redditpost by index number
    fun deletePost(index: Int) {
        if (index in 0 until redditPost.size) {
            redditPost.removeAt(index)
        }
    }
    //Print all posts
    fun printFrontPage() {
        for ((index, post) in redditPost.withIndex())
            println("$index: $post")
    }
}



//--------------------------------------------

//OPGAVE 5
//Write a program that takes a list of words as input and prints
// the frequency of each word.

fun frequency(words: List<String>) {
    val wordFrequency = mutableMapOf<String, Int>()

    words.forEach { word: String ->
        val count = wordFrequency[word] ?: 0
        wordFrequency[word] = count + 1
    }
    println("Word frequencies: ")
    wordFrequency.forEach { (word, frequency) ->
        println("$word: $frequency")
    }
}