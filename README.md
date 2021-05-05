[![Build Status](https://travis-ci.com/maslana146/SE-ScenarioQuality.svg?token=1yYaLppcL54Ax5jwsX1z&branch=main)](https://travis-ci.com/maslana146/SE-ScenarioQuality)
# SE-ScenarioQuality

### Description
For analysts documenting functional requirements with scenarios, our SQC application will provide quantitative information and enable detection of problems in functional requirements written in the form of scenarios. The application will be available via GUI and also as a remote API, thanks to which it can be integrated with existing tools.

### Team
Scrum Master: **Bartosz Maślanka**<br>
Product Owner Proxy: **Hubert Radom**<br>
Software Developer: **Radosław Bodus**<br>
Software Developer: **Kajetan Kubik**


### Example Scenario:
```json
{
  "header": {
    "title": "Book addition",
    "actors": [
      "Librarian"
    ],
    "systemActor": "System"
  },
  "steps": [
    {
      "action": "Librarian selects options to add a new book item"
    },
    {
      "action": "A form is displayed"
    },
    {
      "action": "Librarian provides the details of the book"
    },
    {
      "action": "IF: Librarian wishes to add copies of the book",
      "steps": [
        {
          "action": "Librarian chooses to define instances"
        },
        {
          "action": "System presents defined instances"
        },
        {
          "action": "FOR EACH: instance:",
          "steps": [
            {
              "action": "Librarian chooses to add an instance"
            },
            {
              "action": "System prompts to enter the instance details"
            },
            {
              "action": "Librarian enters the instance details and confirms them"
            },
            {
              "action": "System informs about the correct addition of an instance and presents the updated list of instances"
            }
          ]
        }
      ]
    },
    {
      "action": "Librarian confirms book addition"
    },
    {
      "action": "System informs about the correct addition of the book"
    }
  ]
}
```
