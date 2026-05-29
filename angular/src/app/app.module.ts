import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { WelcomeComponent } from './welcome/welcome.component';
import { NavbarComponent } from './navbar/navbar.component';
import { FooterComponent } from './footer/footer.component';
import { SignupComponent } from './signup/signup.component';
import { LoginComponent } from './login/login.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { CricketteamComponent } from './cricketteam/cricketteam.component';
import { CricketteamlistComponent } from './cricketteam/cricketteamlist.component';
import { UserComponent } from './user/user.component';
import { UserListComponent } from './user/user-list.component';
import { AtmComponent } from './atm/atm.component';

@NgModule({
  declarations: [
    AppComponent,
    WelcomeComponent,
    NavbarComponent,
    FooterComponent,
    SignupComponent,
    LoginComponent,
    CricketteamComponent,
    CricketteamlistComponent,
    UserComponent,
    UserListComponent,
    AtmComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
    
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
