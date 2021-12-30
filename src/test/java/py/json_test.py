#!/usr/bin/python
import json
import requests
import os
import sys




jsonData = '{"a":1,"b":2,"c":3,"d":4,"e":5}'

text = json.loads(jsonData)
print(text)


class Person(object):
    def __init__(self,name,age):
        self.name = name
        self.age = age
    def __repr__(self):
        return 'Person Object name : %s , age : %d' % (self.name,self.age)
# if __name__  == '__main__':
p = Person('Peter',22)
print(p)
print(p.name)

def jprint(obj):
    # create a formatted string of the Python JSON object
    text = json.dumps(obj, sort_keys=True, indent=4)
    print(text)

# get
# response = requests.get("https://test-agent.foodtruck-qa.com/truck-session/bulk-create-input")
# print(response.status_code)
# jprint(response.json())

# create and write content to file
def makefile(path,pathname,content):
    if os.path.exists(path):
        if os.path.isdir(path):
            f = open(pathname,'w+')
            f.write(content)
            f.seek(0)
            read = f.readline()
            f.close()
            print(read)
        else:
            print('please input the dir name')
    else:
        print('the path is not exists')

# path = "./"
# content = "something content"
# makefile(path,"makefile_test.txt",content)

# -----post json body
# inputs={
#            "delivery_zone_id": "3ef3b7ac-0eaa-4f22-87c8-f9e1d1403303",
#            "inputs": [
#                {
#                    "restaurant_name": "Barrio Café",
#                    "truck_session_count": 1
#                }
#            ]
#        }
# headers = {'Content-type': 'application/json'}
# response=requests.post("https://test-agent.foodtruck-qa.com/truck-session/bulk-create",headers=headers,data=json.dumps(inputs))
# print(response.status_code)
# print(response.text)
# jprint(response.json())
#

# ---post file
# files = {'file': open('./Picking List Template.xlsx', 'rb')}
# response=requests.post("https://fleetinv.foodtruck-qa.com/truck-logistic-site/excel/truck-session/check-bulk-upload-picking-list-data",files=files)
# print(response.status_code)
# print(response.text)
# jprint(response.json())
#

# -- download file
# inputs = {
#             "truck_session_ids": [
#                 26718
#             ]
#         }
# headers = {'Content-type': 'application/json'}
# response = requests.put("https://test-agent.foodtruck-qa.com/excel/truck-session/download-picking-list", headers = headers, data = json.dumps(inputs))
# if response.status_code == 200:
#     print ("get picking list template succeed.")
# else:
#     print ("get picking list template failed.")
#     print (response.status_code)
#     sys.exit()
# with open("./Picking List Template.xlsx",'wb') as file:
#      file.write(response.content)
#
#

# string
# TEST_AGENT_URL="https://test-agent.foodtruck-qa.com"
# url = "%s/truck-session/bulk-create" % TEST_AGENT_URL
# print(url)
# print ("fail response: %s" % TEST_AGENT_URL)


# json to custom object
# import json
# from collections import namedtuple
# from json import JSONEncoder
#
# def customStudentDecoder(studentDict):
#     return namedtuple('X', studentDict.keys())(*studentDict.values())
#
# #Assume you received this JSON response
# studentJsonData = '{"rollNumber": 1, "name": "Emma"}'
#
# # Parse JSON into an object with attributes corresponding to dict keys.
# student = json.loads(studentJsonData, object_hook=customStudentDecoder)
#
# print("After Converting JSON Data into Custom Python Object")
# print(student.rollNumber, student.name)

# replace string
delivery_zone_id = "3ef3b7ac-0eaa-4f22-87c8-f9e1d1403303",

inputs={
           "delivery_zone_id": delivery_zone_id,
           "inputs": [
               {
                   "restaurant_name": "Barrio Café",
                   "truck_session_count": 2
               }
           ]
       }

jprint(inputs)