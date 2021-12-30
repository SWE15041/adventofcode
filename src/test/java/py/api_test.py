#!/usr/bin/python
import requests
response = requests.get("https://test-agent.foodtruck-qa.com/truck-session/bulk-create-input")
print(response.status_code)
