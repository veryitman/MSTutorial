//
//  ViewController.swift
//  MSBlog
//
//  Created by mark.zhang on 2019/4/22.
//  Copyright © 2019 veryitman. All rights reserved.
//

import UIKit

class RootTabBarController: UITabBarController {
    override func viewDidLoad() {
        super.viewDidLoad()
        self.view.backgroundColor = .white
        makeSubTabController();
    }
    
    func makeSubTabController() -> Void {
        let homeController = HomeController()
        let categoryController = CategoryController()
        let friendController = FriendController()
        let profileController = ProfileController()
        
        /** Set title */
        homeController.tabBarItem.title = "Home"
        categoryController.tabBarItem.title = "Category"
        friendController.tabBarItem.title = "Friends"
        profileController.tabBarItem.title = "Profile"
        
        /** Set image */
        homeController.tabBarItem.image = UIImage(named: "")
        homeController.tabBarItem.selectedImage = UIImage(named: "")
        
        /**
         let homeNavVC = UINavigationController(rootViewController: homeController)
         let categoryNavVC = UINavigationController(rootViewController: categoryController)
         let friendNavVC = UINavigationController(rootViewController: friendController)
         let profileNavVC = UINavigationController(rootViewController: profileController)
         
         self.viewControllers = [homeNavVC, categoryNavVC, friendNavVC, profileNavVC]
         */
        
        self.viewControllers = [homeController, categoryController, friendController, profileController]
    }
}
