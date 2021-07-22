package com.example.web_app;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// If we are using Rest services then we should go with @RestController but to keep it simple lets use @Controller only.
// Instead of using @Component on a controller class in Spring MVC, we use @Controller, which is more readable and appropriate. Even if you replace
// @Controller annotation with @Component, Spring can automatically detect and register the controller class but it may not work as you expect with
// respect to request mapping

// Diff b/w the annotations: https://stackoverflow.com/questions/6827752/whats-the-difference-between-component-repository-service-annotations-in

@Controller
public class HomeController {


    // If no response hs been returned and you request for this end point : 1) If using @Controller then "Internal Server Error" will
    // be shown as it is the error on server side (as no response) and not on client side. 2) If using @RestController then no
    // error will be thrown and you will see a blank white window.

    // By default Spring boot will search the .jsp page inside the 'src -> main -> webapp -> any_name.jsp'.

    // By default .jsp is not supported as Spring boot responsibility is to return response and not .jsp file. So if you return
    // any thing say here String, if :
    // 1) '@ResponseBody' annotation is there then it will be returned as a response and you will
    // see on the window screen. It because whenever you use '@ResponseBody', it says that whatever you are returning is actually
    // the data and not the page name.
    // 2) If '@ResponseBody' is not there then the String you are returning will return the .jsp page having the same name as string
    // name. But by default as .jsp is not supported so the string you are returning if matches with any .jsp file then that file will
    // get downloaded otherwise (if not matches with any .jsp file) then you will see status 404 not found error.

    // @RequestMapping is used to map the request i.e. here whenever we get '/home', sweet_home() method will be executed.
    // @RequestMapping("home")
    // @ResponseBody
    // public String sweet_home() {
    //    System.out.println("Hey Emilia");
    //
    //    return "hom.jsp";
    // }


    // **************************************** Req and Res using Servlet Concept ************************************************

    // There is a concept of auto configuration in spring boot which will search .jsp file automatically inside the 'webapp' folder.
    // But what if we want to change the folder structure ? We can change it but then we have to explicitly tell the spring boot
    // that we have done some configuration by using 'application.properties' file. Mention every configurations in this file.

    // In spring it's not necessary to create both Request and Response objects. We can create them by passing as an arguments.
    // HttpServletRequest is the request body. It's basically using the Servlet concept. We can skip all these and use the spring
    // boot mvc features.
//    @RequestMapping("home")
//    public String sweet_home(HttpServletRequest request, HttpServletResponse response) {
//        String person_name = request.getParameter("name");
//        System.out.println("Hey " + person_name);
//
//        // Now to send this 'name' variable and show it on the browser we can use 'request.setAttribute()'. We can also use
//        // HttpSession class. Set the attribute and fetch it in the home.jsp file.
//        HttpSession session = request.getSession();
//        session.setAttribute("name", person_name);
//
//        return "home";
//    }

    // **************************************** Req and Res using Servlet Concept ************************************************


    // **************************************** Req and Res using spring mvc *****************************************************

    // Whatever is passed (but only one) as a query param is directly accessible in the sweet_home() method as an argument with the
    // same variable name used in query param. For example we are passing 'name' in the query param then we access it here using same
    // name only. Spring boot will map the query param with the variable used here. But what if we want to change the name of the variable
    // here or if we want to pass more than one as a query params ? We can do it but for that we have to use @RequestParam annotation.
    // We changed the variable name from 'name' to 'person_name'.

    // Anything returned from here will go to 'DispatcherServlet'. 'DispatcherServlet' (a Front Controller) needs to two thing:
    // 1) It needs the data (When we send the 'person_name' is a data and data is a model)
    // 2) View name (when we return the name of the JSP file is a view)
//    @RequestMapping("home")
//    public String sweet_home(@RequestParam("name") String person_name, HttpSession session) {
//
//        System.out.println("Hey " + person_name);
//
//        session.setAttribute("name", person_name);
//
//        return "home";
//    }

    // **************************************** Req and Res using spring mvc *****************************************************


    // **************************************** Using ModelAndView class *****************************************************

    // Anything returned from here will go to 'DispatcherServlet'. 'DispatcherServlet' (a Front Controller) needs to two thing:
    // 1) It needs the data (When we send the 'person_name' is a data and data is a model)
    // 2) View name (when we return the name of the JSP file is a view)

    // We can make the ModelAndView instance and send it instead of sending separately the string name (view) and using the
    // HttpSession instance. We can avoid both HttpSession and Servlet (request and response).
//    @RequestMapping("home")
//    public ModelAndView sweet_home(@RequestParam("name") String person_name) {
//
//        // As name suggests, ModelAndView class holds model and view and we can add as many data (model) we want.
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.addObject("name", person_name);
//        // System.out.println("Hey " + person_name);
//
//        return modelAndView;
//    }

    // **************************************** Using ModelAndView class *****************************************************


    // **************************************** Accepting whole as an object *****************************************************

    // If several query params are passed and all the param variables are the properties (data member) of the any model class we
    // created (here Alien class) then we can directly access all the parameters using class. If we have some extra param variables
    // which are not in the Alien class then they will not be accessible as we are catching Alien class instance only.
    @RequestMapping("home")
    public ModelAndView sweet_home(Alien alien) {

        // As name suggests, ModelAndView class holds model and view and we can add as many data (model) we want.
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("obj", alien);
        // System.out.println("Hey " + person_name);

        return modelAndView;
    }

    // **************************************** Accepting whole as an object *****************************************************
}