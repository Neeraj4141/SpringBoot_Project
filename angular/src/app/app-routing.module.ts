import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { WelcomeComponent } from './welcome/welcome.component';
import { SignupComponent } from './signup/signup.component';
import { LoginComponent } from './login/login.component';
import { CricketteamComponent } from './cricketteam/cricketteam.component';
import { UserComponent } from './user/user.component';
import { UserListComponent } from './user/user-list.component';
import { AtmComponent } from './atm/atm.component';

const routes: Routes = [

  {
    path: '',
    pathMatch: 'full',
    redirectTo: 'welcome'
  },
  {
    path: 'welcome',
    component: WelcomeComponent
  },
  {
    path: 'signup',
    component: SignupComponent
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'cricketteam',
    component: CricketteamComponent
  },
  {
    path: 'user',
    component: UserComponent
  },
  {
    path: 'user-list',
    component: UserListComponent
  },
  {
    path: 'user/:id',
    component: UserComponent
  },
  {
    path:'atm',
    component: AtmComponent
  }


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
