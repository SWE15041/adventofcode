#!/usr/bin/python
import json
import requests
import os
import sys
from collections import namedtuple

TEST_AGENT_URL="https://test-agent.foodtruck-qa.com"
FLEET_INV_URL="https://fleetinv.foodtruck-qa.com"

def TruckSession(object):
    return namedtuple('X', object.keys())(*object.values())
#     def __init__(self,truck_session_ids):
#         self.truck_session_ids = truck_session_ids

def jsonToObject(d):
    #convert dict to object
    if'__class__' in d:
        class_name = d.pop('__class__')
        module_name = d.pop('__module__')
        module = __import__(module_name)
        class_ = getattr(module,class_name)
        args = dict((key.encode('ascii'), value) for key, value in d.items()) #get args
        inst = class_(**args) #create new instance
    else:
        inst = d
    return inst

def jprint(obj):
    # create a formatted string of the Python JSON object
    text = json.dumps(obj, sort_keys=True, indent=4)
    print(text)


# get picking list restaurant
response = requests.get("%s/truck-session/bulk-create-input"%TEST_AGENT_URL)
if response.status_code == 200:
    print ("get picking list restaurant succeed.")
else:
    print ("get picking list restaurant failed.")
    print ("fail response: %s"%response.status_code)
    sys.exit()
jprint(response.json())
restaurant = response.json()

# create truck session
# inputs = json.dumps(restaurant)
inputs ={
            "delivery_zone_id": "3ef3b7ac-0eaa-4f22-87c8-f9e1d1403303",
            "inputs": [
                {
                    "restaurant_name": "Barrio Café",
                    "truck_session_count": 1
                }
            ]
        }
# inputs = restaurant
# inputs = {"inputs": [{"restaurant_name": "Bar Nakazawa", "truck_session_count": 8}, {"restaurant_name": "Barrio Caf\u00e9", "truck_session_count": 12}, {"restaurant_name": "Bobby Flay Steak", "truck_session_count": 6}, {"restaurant_name": "Chios Taverna by Michael Symon", "truck_session_count": 6}, {"restaurant_name": "Di Fara Pizza", "truck_session_count": 8}, {"restaurant_name": "Fred's Meat & Bread", "truck_session_count": 8}, {"restaurant_name": "Maydan", "truck_session_count": 6}, {"restaurant_name": "Pennycress by Seamus Mullen", "truck_session_count": 4}, {"restaurant_name": "Pizzeria Mozza", "truck_session_count": 8}, {"restaurant_name": "SriPraPhai", "truck_session_count": 6}, {"restaurant_name": "Taqueria del D\u00eda", "truck_session_count": 6}, {"restaurant_name": "The General Chinese", "truck_session_count": 12}, {"restaurant_name": "The Mainstay by Marc Murphy", "truck_session_count": 6}, {"restaurant_name": "The Regular", "truck_session_count": 4}]}

print(inputs)
headers = {'Content-type': 'application/json'}
response = requests.post("%s/truck-session/bulk-create"%TEST_AGENT_URL, headers = headers, json = inputs)
if response.status_code == 201:
    print ("create truck session succeed.")
else:
    print ("create truck session failed.")
    print (response)
    print ("fail response: %s" % response.status_code)
    sys.exit()

# truckSession = json.loads(jsonToObject(response.text), object_hook=TruckSession)
truckSession = response.json()
print("create truck session response:\n")
jprint (response.json())

# get picking list template
# inputs = {
#             "truck_session_ids": [
#                 26735
#             ]
#         }
# inputs = truckSession.truck_session_ids
# print (inputs)
headers = {'Content-type': 'application/json'}
response = requests.put("%s/excel/truck-session/download-picking-list"%TEST_AGENT_URL, headers = headers, json = truckSession)
if response.status_code == 200:
    print ("get picking list template succeed.")
else:
    print ("get picking list template failed.")
    print ("fail response: %s" % response.status_code)
    sys.exit()
with open("./Picking List Template.xlsx",'wb') as file:
     file.write(response.content)

# upload picking list (disable)
files = {'file': open('./Picking List Template.xlsx', 'rb')}
response=requests.post("%s/truck-logistic-site/excel/truck-session/bulk-upload-picking-list"%FLEET_INV_URL,files = files)
if response.status_code == 200:
    print ("upload picking list succeed.")
else:
    print ("upload picking list failed.")
    print ("fail response: %s" % response.status_code)
    sys.exit()
jprint(response.json())


# check picking list
files = {'file': open('./Picking List Template.xlsx', 'rb')}
response=requests.post("%s/truck-logistic-site/excel/truck-session/check-bulk-upload-picking-list-data"%FLEET_INV_URL,files = files)
if response.status_code == 200:
    print ("check picking list succeed.")
else:
    print ("check picking list failed.")
    print ("fail response: %s" % response.status_code)
    sys.exit()
jprint(response.json())
