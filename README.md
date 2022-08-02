# ai_chat_bot
An A.I. chat bot that responds to anything the user says. It also expresses visual emotions which were drawn by me!
It begins by taking the user's input and putting it through a series of filters and using an algorithm to respond to the user with the most relevant and appropriate response:

1. The first filter checks if the user input is exactly the same as the pre-written to which it will respond to the user
correctly (highest confidence rate)

2. The second filter will scower for specific phrasing (i.e. 'what are you') and respond based off that (not-so bad confidence rate)

3. The third filter will search for key words (i.e. 'you') and respond based off that (lowest confidence rate)

4. And if all else fails, the chat bot will ask the user to explain what they mean by that. Then if the user says the same thing, the chat bot
will respond exactly what the user had explained earlier.
