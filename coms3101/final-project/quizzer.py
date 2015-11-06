from datetime import datetime
from pymongo import MongoClient
from random import shuffle

client = MongoClient()
db = client.quizzer
topic = input("Which topic would you like to quiz? ")
collection = db[topic]

print("\nIf you'd like to add terms, enter the first word.\n"
            "Press enter at any point to skip or exit.\n")
term = input("Term: ")

while term:
    definition = input("Definition: ")
    entry = {"term": term,
            "definition": definition,
            "last_updated": datetime.now()}
    collection.insert(entry)
    term = input("Term: ")

choice = print("\nIf you'd like to update terms, enter the word you'd like "
            "to update.\nPress enter at any point to skip or exit.\n")

term = input("Term: ")
while term:
    definition = input("Definition: ")
    entry = {"term": term,
            "$set": {
                "definition": definition 
            },
            "$currentDate": {
                "last_updated": True
            }}
    update = collection.update_one({"term": term},
                            {"$set": {
                                "definition": definition
                            }})
    if update.modified_count > 0:
        print("Success! That term was updated.\n")
    else:
        print("Hm, that didn't work. Was that term ever added?\n")
    term = input("Term: ")

choice = print("\nIf you'd like to delete terms, enter the word you'd like "
            "to delete.\nPress enter at any point to skip or exit.\n")

term = input("Term: ")
while term:
    delete = collection.delete_one({"term": term})
    if delete.deleted_count > 0:
        print("Success! That term was deleted.\n")
    else:
        print("Hm, that didn't work. Was that term ever added?\n")
    term = input("Term: ")

print("\nOkay, sounds good! Now let's get to the quizzing. Enter the word "
    "for each term that you see.\n")

data = collection.find()
terms = []
for entry in data:
    terms.append({"term": entry["term"],
                "definition": entry["definition"]})
review_list = []
right, wrong = 0, 0
shuffle(terms)

if terms:
    print(terms[right + wrong]["definition"])
    answer = input("Term: ")

    while answer and right + wrong < len(terms):
        if terms[right + wrong]["term"].lower() == answer.lower():
            print("Perfect!\n")
            right += 1
        else:
            print("Whoops, that wasn't it. The answer was \"%s.\"\n" %
                terms[right + wrong]["term"])
            wrong += 1
            review_list.append(terms[right + wrong])
        if right + wrong < len(terms):
            print(terms[right + wrong]["definition"])
            answer = input("Term: ")

    print("\nGreat work this session!\n\nCorrect: %d%%\nIncorrect: %d%%" %
        ((right / (right + wrong)) * 100, (wrong / (right + wrong)) * 100))

    if review_list:
        print("\nHere's a list of words you should review.")
        for term in review_list:
            print(term["term"])
            print(term["definition"])