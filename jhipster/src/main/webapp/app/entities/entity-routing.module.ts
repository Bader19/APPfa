import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'espace-vert',
        data: { pageTitle: 'pfaApp.espaceVert.home.title' },
        loadChildren: () => import('./espace-vert/espace-vert.module').then(m => m.EspaceVertModule),
      },
      {
        path: 'zone',
        data: { pageTitle: 'pfaApp.zone.home.title' },
        loadChildren: () => import('./zone/zone.module').then(m => m.ZoneModule),
      },
      {
        path: 'type-sol',
        data: { pageTitle: 'pfaApp.typeSol.home.title' },
        loadChildren: () => import('./type-sol/type-sol.module').then(m => m.TypeSolModule),
      },
      {
        path: 'plante',
        data: { pageTitle: 'pfaApp.plante.home.title' },
        loadChildren: () => import('./plante/plante.module').then(m => m.PlanteModule),
      },
      {
        path: 'utilisateur',
        data: { pageTitle: 'pfaApp.utilisateur.home.title' },
        loadChildren: () => import('./utilisateur/utilisateur.module').then(m => m.UtilisateurModule),
      },
      {
        path: 'capteur',
        data: { pageTitle: 'pfaApp.capteur.home.title' },
        loadChildren: () => import('./capteur/capteur.module').then(m => m.CapteurModule),
      },
      {
        path: 'boitier',
        data: { pageTitle: 'pfaApp.boitier.home.title' },
        loadChildren: () => import('./boitier/boitier.module').then(m => m.BoitierModule),
      },
      {
        path: 'grandeur',
        data: { pageTitle: 'pfaApp.grandeur.home.title' },
        loadChildren: () => import('./grandeur/grandeur.module').then(m => m.GrandeurModule),
      },
      {
        path: 'type-plante',
        data: { pageTitle: 'pfaApp.typePlante.home.title' },
        loadChildren: () => import('./type-plante/type-plante.module').then(m => m.TypePlanteModule),
      },
      {
        path: 'plantation',
        data: { pageTitle: 'pfaApp.plantation.home.title' },
        loadChildren: () => import('./plantation/plantation.module').then(m => m.PlantationModule),
      },
      {
        path: 'arrosage',
        data: { pageTitle: 'pfaApp.arrosage.home.title' },
        loadChildren: () => import('./arrosage/arrosage.module').then(m => m.ArrosageModule),
      },
      {
        path: 'connecte',
        data: { pageTitle: 'pfaApp.connecte.home.title' },
        loadChildren: () => import('./connecte/connecte.module').then(m => m.ConnecteModule),
      },
      {
        path: 'installation',
        data: { pageTitle: 'pfaApp.installation.home.title' },
        loadChildren: () => import('./installation/installation.module').then(m => m.InstallationModule),
      },
      {
        path: 'extra-user',
        data: { pageTitle: 'pfaApp.extraUser.home.title' },
        loadChildren: () => import('./extra-user/extra-user.module').then(m => m.ExtraUserModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class EntityRoutingModule {}
